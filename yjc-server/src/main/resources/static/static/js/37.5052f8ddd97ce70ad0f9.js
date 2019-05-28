webpackJsonp([37],{UE5N:function(e,t){},xdD3:function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var s=a("mvHQ"),i=a.n(s),n=a("GgDs"),o=a("VsUZ"),d=a("Au9i"),c={name:"AddPatient",components:{Header:n.a,Toast:d.Toast},data:function(){return{slots:[{values:["身份证","驾驶证","护照"],textAlign:"center"}],isShowPicker:!1,isSelect:!0,isUpdate:!1,headerCon:"",selectImg:a("ahnF"),notSelectImg:a("CHIo"),selectCertificatesType:"请选择",addInfo:{name:"",sex:"男",age:0,phone:"",certificatesType:"身份证",certificatesNo:"",address:""}}},methods:{onValuesChange:function(e,t){this.selectCertificatesType=t[0],console.log(t)},getCertificatesType:function(e){this.selectCertificatesType=e},changeGender:function(){this.isSelect=!this.isSelect,this.isSelect?this.addInfo.sex="男":this.addInfo.sex="女"},judge:function(){return this.addInfo.name.length<=1?(Object(d.Toast)({message:"请输入您的姓名",position:"bottom",duration:3e3}),!1):this.addInfo.age<=0?(Object(d.Toast)({message:"请输入正确的年龄",position:"bottom",duration:3e3}),!1):11!==this.addInfo.phone.length?(Object(d.Toast)({message:"请输入正确联系方式",position:"bottom",duration:3e3}),!1):"请选择"===this.selectCertificatesType?(Object(d.Toast)({message:"请选择证件类型",position:"bottom",duration:3e3}),!1):this.addInfo.certificatesNo.length<=16?(Object(d.Toast)({message:"请输入正确的证件号码",position:"bottom",duration:3e3}),!1):!!this.addInfo.address||(Object(d.Toast)({message:"请输入正确地址",position:"bottom",duration:3e3}),!1)},addPatient:function(e){var t=this;this.judge()&&(e.certificatesType=this.selectCertificatesType,o.a.addPatient(i()(e)).then(function(e){200===e.data.code&&(Object(d.Toast)({message:"添加成功",position:"bottom",duration:3e3}),t.$router.push("/patientList"))}).catch(function(e){}))},updatePatient:function(e){var t=this;e.certificatesType=this.selectCertificatesType,o.a.updatePatient(i()(e)).then(function(e){200===e.data.code&&(Object(d.Toast)({message:"修改成功",position:"bottom",duration:3e3}),t.$router.push("/patientList"),sessionStorage.removeItem("upDatePatient"))}).catch(function(e){})},showCerType:function(){this.isShowPicker=!0},hidePicker:function(){this.isShowPicker=!1}},mounted:function(){this.selectCertificatesType="请选择";var e=JSON.parse(sessionStorage.getItem("upDatePatient"));e?(this.headerCon=e.title,this.addInfo=e.updateInfo,this.selectCertificatesType=this.addInfo.certificatesType,this.isUpdate=!0,"男"===e.updateInfo.sex?this.isSelect=!0:this.isSelect=!1):(this.isUpdate=!1,this.headerCon="添加就医人")}},r={render:function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"add_jyr"},[a("Header",{attrs:{headerCon:e.headerCon}}),e._v(" "),a("p",{staticClass:"name"},[a("span",{staticClass:"name_tit"},[e._v("姓名")]),e._v(" "),a("input",{directives:[{name:"model",rawName:"v-model",value:e.addInfo.name,expression:"addInfo.name"}],attrs:{type:"text",placeholder:"请输入您的姓名"},domProps:{value:e.addInfo.name},on:{input:function(t){t.target.composing||e.$set(e.addInfo,"name",t.target.value)}}})]),e._v(" "),a("div",{staticClass:"gender"},[a("p",[e.isSelect?a("img",{attrs:{src:e.selectImg},on:{click:e.changeGender}}):e._e(),e._v(" "),e.isSelect?e._e():a("img",{attrs:{src:e.notSelectImg},on:{click:e.changeGender}}),e._v(" "),a("span",[e._v("男")])]),e._v(" "),a("p",[e.isSelect?e._e():a("img",{attrs:{src:e.selectImg},on:{click:e.changeGender}}),e._v(" "),e.isSelect?a("img",{attrs:{src:e.notSelectImg},on:{click:e.changeGender}}):e._e(),e._v(" "),a("span",[e._v("女")])])]),e._v(" "),a("p",{staticClass:"name"},[a("span",{staticClass:"name_tit"},[e._v("年龄")]),e._v(" "),a("input",{directives:[{name:"model",rawName:"v-model",value:e.addInfo.age,expression:"addInfo.age"}],attrs:{type:"text",placeholder:"请输入本人年龄"},domProps:{value:e.addInfo.age},on:{input:function(t){t.target.composing||e.$set(e.addInfo,"age",t.target.value)}}})]),e._v(" "),a("p",[a("span",[e._v("联系方式")]),e._v(" "),a("input",{directives:[{name:"model",rawName:"v-model",value:e.addInfo.phone,expression:"addInfo.phone"}],attrs:{type:"text",placeholder:"请输入电话号码"},domProps:{value:e.addInfo.phone},on:{input:function(t){t.target.composing||e.$set(e.addInfo,"phone",t.target.value)}}})]),e._v(" "),a("div",{staticClass:"zjlx",on:{click:e.showCerType}},[a("span",[e._v("证件类型")]),e._v(" "),a("span",{staticClass:"select_certi_type"},[e._v(e._s(e.selectCertificatesType))])]),e._v(" "),a("p",[a("span",[e._v("证件号码")]),e._v(" "),a("input",{directives:[{name:"model",rawName:"v-model",value:e.addInfo.certificatesNo,expression:"addInfo.certificatesNo"}],attrs:{type:"text",placeholder:"请输入证件号码"},domProps:{value:e.addInfo.certificatesNo},on:{input:function(t){t.target.composing||e.$set(e.addInfo,"certificatesNo",t.target.value)}}})]),e._v(" "),a("p",[a("span",[e._v("家庭住址")]),e._v(" "),a("input",{directives:[{name:"model",rawName:"v-model",value:e.addInfo.address,expression:"addInfo.address"}],attrs:{type:"text",placeholder:"请输入您的家庭地址"},domProps:{value:e.addInfo.address},on:{input:function(t){t.target.composing||e.$set(e.addInfo,"address",t.target.value)}}})]),e._v(" "),a("button",{directives:[{name:"show",rawName:"v-show",value:!e.isUpdate,expression:"!isUpdate"}],on:{click:function(t){e.addPatient(e.addInfo)}}},[e._v("确定")]),e._v(" "),a("button",{directives:[{name:"show",rawName:"v-show",value:e.isUpdate,expression:"isUpdate"}],on:{click:function(t){e.updatePatient(e.addInfo)}}},[e._v("修改")]),e._v(" "),a("div",{directives:[{name:"show",rawName:"v-show",value:e.isShowPicker,expression:"isShowPicker"}],staticClass:"outerPicker",on:{click:e.hidePicker}},[a("mt-picker",{attrs:{slots:e.slots},on:{change:e.onValuesChange}})],1)],1)},staticRenderFns:[]};var p=a("VU/8")(c,r,!1,function(e){a("UE5N")},"data-v-4852adb9",null);t.default=p.exports}});