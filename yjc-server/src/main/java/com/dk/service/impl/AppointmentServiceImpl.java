package com.dk.service.impl;

import cn.sourcespro.commons.service.IdWoker;
import cn.sourcespro.commons.utils.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dk.data.dao.AppointmentMapper;
import com.dk.data.dao.NurseAppointmentMapper;
import com.dk.data.dao.NurseMapper;
import com.dk.data.dao.OrderMapper;
import com.dk.data.dto.SearchAppointmentDto;
import com.dk.data.entity.Appointment;
import com.dk.data.entity.Nurse;
import com.dk.data.entity.NurseAppointment;
import com.dk.data.entity.Order;
import com.dk.response.HttpStateCode;
import com.dk.service.AppointmentService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 预约 ServiceImpl
 *
 * @author ban
 * @date 2018/12/12
 */
@Service
public class AppointmentServiceImpl implements AppointmentService {

    private static final Logger logger = LoggerFactory.getLogger(AppointmentServiceImpl.class);

    @Autowired
    private AppointmentMapper mapper;

    @Autowired
    private NurseAppointmentMapper nurseAppointmentMapper;

    @Autowired
    private NurseMapper nurseMapper;

    @Autowired
    private IdWoker idWoker;

    @Override
    public Appointment add(Appointment bean) {

        initBasicVals(bean);

        int result = mapper.insert(bean);
        if (result > 0) {
            return bean;
        }
        return null;
    }

    @Override
    public Appointment update(Appointment bean) {
        Long id = bean.getId();
        if (id == null) {
            return null;
        }
        Appointment dbAppointment = mapper.selectById(id);
        if (dbAppointment == null) {
            return null;
        }
        dbAppointment.setUpdateTime(new Date());
        BeanUtil.copyProperties(bean, dbAppointment);
        int result = mapper.updateById(dbAppointment);
        if (result > 0){
            return dbAppointment;
        }
        return null;
    }

    @Override
    public Appointment findById(Long id) {
        return mapper.selectById(id);
    }

    @Override
    public Appointment findByUuid(String uuid) {
        return mapper.findByUuid(uuid);
    }

    @Override
    public IPage<Appointment> findAll(SearchAppointmentDto condition) {
        Page page = condition.getPage();
        page.setTotal(mapper.count(condition));
        return mapper.findAll(page, condition);
    }

    @Override
    public int count(SearchAppointmentDto condition) {
        return mapper.count(condition);
    }

    @Override
    public int batchInsert(List<Appointment> list) {
        for (Appointment bean : list) {
            initBasicVals(bean);
        }
        return mapper.batchInsert(list);
    }

    @Override
    public List<Appointment> batchQueryByIds(List<Long> ids) {
        return mapper.batchQueryByIds(ids);
    }

    @Override
    public List<Appointment> batchQueryByUuids(List<String> uuids) {
        return mapper.batchQueryByUuids(uuids);
    }

    @Override
    public int delete(Long id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int delete(String uuid) {
        return mapper.deleteByUuid(uuid);
    }

    @Override
    public int delete(Appointment bean) {
        return delete(bean.getId());
    }

    @Override
    public int batchDeleteById(List<Long> ids) {
        if (CollectionUtils.isNotEmpty(ids)) {
            return mapper.batchDeleteById(ids);
        }
        return 0;
    }

    @Override
    public int batchDeleteByUuid(List<String> uuids) {
        if (CollectionUtils.isNotEmpty(uuids)) {
            return mapper.batchDeleteByUuid(uuids);
        }
        return 0;
    }

    @Override
    public IPage<Appointment> findAllByUser(SearchAppointmentDto condition) {
        Page page = condition.getPage();
        page.setTotal(mapper.countByUser(condition));
        return mapper.findAllByUser(page, condition);
    }

    @Override
    public Appointment findByOrderList(String orderList) {
        return mapper.findByOrderList(orderList);
    }

    @Override
    public Boolean isExtraPrice(String uuid) {
        Appointment appointment = mapper.findByOrderList(uuid);
        Date appointmentTime = appointment.getAppointmentTime();
        if (appointment.getStatus() == 1) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String timeStr = sdf.format(appointmentTime);
            long time = 0;
            try {
                time = sdf.parse(timeStr).getTime();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (System.currentTimeMillis() > time - 14400000) {
               return true;
            }
        }
        return false;
    }

    /*
     * @Description 定时标记超时订单
     * @Date 2019/1/15
     * @Param
     * @return
     * @Author zhy
     **/
    @Scheduled(fixedRate = 1000 * 60)
    public void timeout() {
        List<Appointment> appointments = mapper.findByStatus(Byte.valueOf("0"));
        Nurse nurse = null;
        if (appointments != null) {
            for (Appointment appointment : appointments) {
                // 标记超时
                NurseAppointment ns = nurseAppointmentMapper.findByStatusAndAppointment(Byte.valueOf("0"), appointment.getUuid());
                if (ns != null) {
                    Long time = ns.getCreateTime().getTime();
                    Long nowTime = System.currentTimeMillis();
                    if (nowTime > time + 300000) {
                        ns.setStatus(Byte.valueOf("2"));
                        ns.setUpdateTime(new Date());
                        nurseAppointmentMapper.updateById(ns);
                    }
                }
            }
        }
        autoAllocation();
    }

    @Override
    public void autoAllocation() {
        List<Appointment> appointments = mapper.findByStatus(Byte.valueOf("0"));
        Nurse nurse = null;
        if (appointments != null) {
            for (Appointment appointment : appointments) {
                // timeout();
                // 派单
                NurseAppointment nurseAppointment = nurseAppointmentMapper.findByAppointment(appointment.getUuid());
                if (nurseAppointment == null) {
                    nurse = getNurse();
                    if (nurse != null) {
                        NurseAppointment bean = new NurseAppointment();
                        bean.setUuid(idWoker.nextStringId());
                        bean.setNurse(nurse.getUuid());
                        bean.setAppointment(appointment.getUuid());
                        bean.setCreateTime(new Date());
                        bean.setUpdateTime(new Date());
                        bean.setStatus(Byte.valueOf("0"));
                        bean.setDeleted(false);
                        nurseAppointmentMapper.insert(bean);
                    }
                }
            }
        }
    }

    private Nurse nurse;
    private Queue<Nurse> queue;

    private Nurse getNurse() {
        if (queue == null) {
            queue = new LinkedList<>();
        }
        List<Nurse> nurses = nurseMapper.findByStatus(Byte.valueOf("3"));
        if (nurses != null) {
            nurse = queue.poll();
            if (nurse == null) {
                queue = new LinkedList<>();
                for (Nurse nur : nurses) {
                    queue.offer(nur);
                }
                nurse = queue.poll();
            }
             return nurse;
        } else {
            return null;
        }
    }

    private void initBasicVals(Appointment bean){
        bean.setUuid(idWoker.nextStringId());
        bean.setCreateTime(new Date());
        bean.setUpdateTime(new Date());
        bean.setTotal(1);
        bean.setStatus(Byte.valueOf("0"));
        bean.setDeleted(false);
    }
}