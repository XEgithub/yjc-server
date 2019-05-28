webpackJsonp([19],{"+SO9":function(t,s,i){"use strict";Object.defineProperty(s,"__esModule",{value:!0});var e=i("mvHQ"),a=i.n(e),o=i("GgDs"),r=i("Au9i"),n=i("VsUZ"),p={name:"AppointmentConfirm",components:{Header:o.a,MessageBox:r.MessageBox,Toast:r.Toast},data:function(){return{headerCon:"订单详情",isAlreadAppoint:!0,isShowModal:!1,isShowBuy:!1,isShowRefund:!1,isShowAppointTime:!1,delIcon:i("Ub/c"),identifyIcon:i("VLEx"),appointment:{},order:{},patient:{},projectList:{},paperReport:"需要",hospitalList:this.common.getStorage("hospitalList")}},methods:{goAppointment:function(){this.$router.push({path:"/immediaterreservation",query:{uuid:this.$route.query.uuid}})},goRefund:function(){console.log("敬请期待666")},getOrderDetail:function(){var t=this,s={uuid:this.$route.query.uuid};n.a.getOrderDetail(s).then(function(s){if(console.log(s),200===s.data.code){var i=s.data.successObject,e=i.appointment,o=i.order,r=i.patient,n=i.projectList;t.appointment=e,t.order=o,t.patient=r,t.projectList=n,"0"===o.paperReport?t.paperReport="不需要":"1"===o.paperReport&&(t.paperReport="需要");for(var p=0;p<t.projectList.length;p++)for(var c=0;c<t.hospitalList.length;c++)parseInt(t.projectList[p].hospital)===parseInt(t.hospitalList[c].uuid)&&(t.projectList[p].hospitalName=t.hospitalList[c].name);console.log(t.hospitalList),sessionStorage.setItem("projectList",a()(t.projectList))}})},cancel:function(){var t={uuid:this.order.appointment};console.log(t.uuid);var s={order:this.order,projectList:this.projectList};sessionStorage.setItem("refundInfo",a()(s)),this.$router.push({path:"/refund",query:{uuid:this.$route.query.uuid}})},cancelConfirm:function(){var t=this,s={uuid:this.order.appointment};console.log(s.uuid),n.a.cancelAppointment(s).then(function(s){200===s.data.code&&(console.log(s.data),Object(r.Toast)({message:"您已取消预约",position:"bottom",duration:3e3}),t.isShowBuy=!0)}).catch(function(t){Object(r.Toast)({message:"网络错误，请重试",position:"bottom",duration:3e3})})},again:function(){var t={uuid:this.$route.query.uuid};this.$router.push({path:"/immediaterreservation",query:{uuid:t.uuid,appointmentTime:!0}})}},mounted:function(){this.getOrderDetail();var t=this.$route.query.status;2===parseInt(t)?(this.isAlreadAppoint=!0,this.isShowRefund=!0):3===parseInt(t)&&(this.isShowAppointTime=!0,this.isAlreadAppoint=!1,this.isShowRefund=!0)}},c={render:function(){var t=this,s=t.$createElement,i=t._self._c||s;return i("div",{staticClass:"appointment_confirm"},[i("Header",{attrs:{headerCon:t.headerCon}}),t._v(" "),i("div",{staticClass:"tips"}),t._v(" "),i("div",{staticClass:"patient"},[i("img",{staticClass:"iden",attrs:{src:t.identifyIcon}}),t._v(" "),i("div",{staticClass:"pa_top"},[i("p",[t._v(t._s(t.patient.name))]),t._v(" "),i("p",[t._v(t._s(t.patient.phone))])]),t._v(" "),i("div",{staticClass:"pa_bot"},[i("p",[t._v(t._s(t.patient.certificatesType))]),t._v(" "),i("p",[t._v(t._s(t.patient.certificatesNo))])])]),t._v(" "),i("div",{staticClass:"info"},[i("div",{staticClass:"order_num"},[i("p",[t._v("订单号：")]),t._v(" "),i("p",[t._v(t._s(t.order.orderNo))])]),t._v(" "),i("div",{staticClass:"pro_info"},[i("p",{staticClass:"tit"},[t._v("项目信息:")]),t._v(" "),t._l(t.projectList,function(s,e){return i("p",{key:e,staticClass:"pro_list"},[i("span",[t._v(t._s(s.name))]),t._v(" "),i("span",[t._v(t._s(s.hospitalName))]),t._v(" "),i("span",[t._v("周期："+t._s(s.period))])])})],2),t._v(" "),i("div",{staticClass:"user_remarks"},[i("p",{staticClass:"tit"},[t._v("用户备注:")]),t._v(" "),i("p",{staticClass:"remark_list"},[i("span",[t._v(t._s(t.order.userRemark))])])]),t._v(" "),i("div",{staticClass:"service_position"},[i("p",{staticClass:"tit"},[t._v("服务位置:")]),t._v(" "),i("p",{staticClass:"remark_list"},[i("span",[t._v(t._s(t.order.address))])])]),t._v(" "),i("div",{staticClass:"service_position"},[i("p",{staticClass:"tit"},[t._v("纸质报告:")]),t._v(" "),i("p",{staticClass:"remark_list"},[i("span",[t._v(t._s(t.paperReport))])])]),t._v(" "),i("div",{staticClass:"service_position"},[i("p",{staticClass:"tit yhj"},[t._v("优惠券:")]),t._v(" "),i("p",{staticClass:"remark_list"},[i("span",[t._v(t._s(t.order.coupon))])])]),t._v(" "),i("div",{staticClass:"service_position"},[i("p",{staticClass:"tit"},[t._v("注意事项:")]),t._v(" "),i("p",{staticClass:"remark_list"},[i("span",[t._v(t._s(t.order.notes))])])]),t._v(" "),t.isShowAppointTime?i("div",{staticClass:"service_position"},[i("p",{staticClass:"tit"},[t._v("预约时间:")]),t._v(" "),i("p",{staticClass:"remark_list"},[i("span",[t._v(t._s(t.appointment.appointmentTime))])])]):t._e()]),t._v(" "),i("div",{staticClass:"footer"},[i("button",{directives:[{name:"show",rawName:"v-show",value:t.isShowRefund,expression:"isShowRefund"}],staticClass:"cancel",on:{click:t.cancel}},[t._v("申请退款")]),t._v(" "),i("button",{directives:[{name:"show",rawName:"v-show",value:t.isAlreadAppoint,expression:"isAlreadAppoint"}],staticClass:"confirm",on:{click:t.goAppointment}},[t._v("去预约")]),t._v(" "),i("button",{directives:[{name:"show",rawName:"v-show",value:!t.isAlreadAppoint,expression:"!isAlreadAppoint"}],staticClass:"again",on:{click:t.again}},[t._v("重新预约")]),t._v(" "),i("img",{directives:[{name:"show",rawName:"v-show",value:t.isShowBuy,expression:"isShowBuy"}],staticClass:"del_appoint",attrs:{src:t.delIcon}}),t._v(" "),i("button",{directives:[{name:"show",rawName:"v-show",value:t.isShowBuy,expression:"isShowBuy"}],staticClass:"again_buy"},[t._v("再次购买")])])],1)},staticRenderFns:[]};var u=i("VU/8")(p,c,!1,function(t){i("uPDp")},"data-v-66aa5e86",null);s.default=u.exports},"Ub/c":function(t,s){t.exports="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACQAAAAkCAMAAADW3miqAAAAZlBMVEUAAACqqqqqqqqqqqqqqqqpqamrq6uqqqqqqqqpqampqamoqKiqqqqrq6uZmZmpqamqqqqpqamnp6erq6urq6usrKyqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqpqampqamqqqqI260dAAAAIXRSTlMAVzOs5N9S+WOnOAb3fwWVGysLpXAR7LeWi1xD19LIaMF/FG+JAAAAxElEQVQ4y+3UyQ6CMACE4ZFCqxaVsgq4zfu/pKEqUJYQDh5M/C4Tmp+QcCg68uCoMoxkNw74BkOCVA7yggGt+Mh0X0nu4YrIFI49KddFxmvUZOk5KjL1rBgnLrpBcNEVsdwsibDCJ45had0+du5M7BahtK/kQWaP1RmdHf1mjor2dPP+2YL+RBTy1Kxso+Af/U50no+SV4Sch2Zq0sxHptJ2ZY3pz7m+GiXMMaFwLpaUFNuRkhToxAEnhQY9UaE4dvVgPQHemiq+oGx8QgAAAABJRU5ErkJggg=="},uPDp:function(t,s){}});