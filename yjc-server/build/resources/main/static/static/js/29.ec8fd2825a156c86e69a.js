webpackJsonp([29],{fPME:function(e,t){},lzg9:function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var i=a("mvHQ"),s=a.n(i),n=a("VsUZ"),o={name:"",props:[],data:function(){return{sexList:["男","女"],currentSex:"男",currentIndex:0,IDtype:"",identityList:[{flex:1,visibleItemCount:1,value:"ddd",values:["身份证","驾驶证","护照"]}],hospital:this.common.getStorage("hospitalList"),hospitalList:[{flex:1,visibleItemCount:1,value:"ddd",values:[]}],identityType:0,hospitalType:0,Htype:"",finalHospitalList:[],name:"",age:"",phone:"",certificatesNo:"",address:""}},methods:{changeSex:function(e,t){this.currentIndex=e,this.currentSex=t},onValuesIdentityChange:function(e,t){this.IDtype=t[0]},changeidentityType:function(e){this.identityType=e},onValuesHospitalChange:function(e,t){this.Htype=t[0]},changeHospitalType:function(e){this.hospitalType=e},submitBasicInfo:function(){var e=this,t={logo:"",name:this.name,sex:this.currentSex,age:this.age,phone:this.phone,certificatesType:this.IDtype,certificatesNo:this.certificatesNo,address:this.address,hospital:this.Htype.value};n.a.addPersonalInfo(s()(t)).then(function(t){200===t.data.code?e.$router.push({path:"/certificate"}):e.common.toast(t.data.msg)}).catch(function(t){e.common.toast(t)})}},created:function(){for(var e in this.hospital){var t={name:this.hospital[e].name,value:this.hospital[e].uuid};this.finalHospitalList.push(t)}this.hospitalList[0].values=this.finalHospitalList.concat()}},l={render:function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"basic_info"},[e._m(0),e._v(" "),a("div",{staticClass:"basic_wrapper common_wrapper"},[a("div",{staticClass:"item"},[a("label",[e._v("姓名")]),e._v(" "),a("p",[a("input",{directives:[{name:"model",rawName:"v-model",value:e.name,expression:"name"}],attrs:{type:"text",name:""},domProps:{value:e.name},on:{input:function(t){t.target.composing||(e.name=t.target.value)}}})])]),e._v(" "),a("div",{staticClass:"item sex_box"},e._l(e.sexList,function(t,i){return a("div",{key:i,staticClass:"sex_select",class:e.currentIndex===i?"selected":"",on:{click:function(a){e.changeSex(i,t)}}},[a("label",[e._v(e._s(t))])])}),0),e._v(" "),a("div",{staticClass:"item"},[a("label",[e._v("年龄")]),e._v(" "),a("p",[a("input",{directives:[{name:"model",rawName:"v-model",value:e.age,expression:"age"}],attrs:{type:"text",name:""},domProps:{value:e.age},on:{input:function(t){t.target.composing||(e.age=t.target.value)}}})])]),e._v(" "),a("div",{staticClass:"item"},[a("label",[e._v("手机号码")]),e._v(" "),a("p",[a("input",{directives:[{name:"model",rawName:"v-model",value:e.phone,expression:"phone"}],attrs:{type:"text",name:""},domProps:{value:e.phone},on:{input:function(t){t.target.composing||(e.phone=t.target.value)}}})])])]),e._v(" "),a("div",{staticClass:"other_wrapper common_wrapper"},[a("div",{staticClass:"item"},[a("label",[e._v("证件类型")]),e._v(" "),a("p",[a("input",{directives:[{name:"model",rawName:"v-model",value:e.IDtype,expression:"IDtype"}],attrs:{type:"text",name:""},domProps:{value:e.IDtype},on:{focus:function(t){e.changeidentityType(1)},input:function(t){t.target.composing||(e.IDtype=t.target.value)}}})])]),e._v(" "),a("div",{staticClass:"item"},[a("label",[e._v("证件号码")]),e._v(" "),a("p",[a("input",{directives:[{name:"model",rawName:"v-model",value:e.certificatesNo,expression:"certificatesNo"}],attrs:{type:"text",name:""},domProps:{value:e.certificatesNo},on:{input:function(t){t.target.composing||(e.certificatesNo=t.target.value)}}})])]),e._v(" "),a("div",{staticClass:"item"},[a("label",[e._v("居住地址")]),e._v(" "),a("p",[a("input",{directives:[{name:"model",rawName:"v-model",value:e.address,expression:"address"}],attrs:{type:"text",name:""},domProps:{value:e.address},on:{input:function(t){t.target.composing||(e.address=t.target.value)}}})])]),e._v(" "),a("div",{staticClass:"item"},[a("label",[e._v("就职医院")]),e._v(" "),a("p",[a("input",{directives:[{name:"model",rawName:"v-model",value:e.Htype.name,expression:"Htype.name"}],attrs:{type:"",name:""},domProps:{value:e.Htype.name},on:{focus:function(t){e.changeHospitalType(1)},input:function(t){t.target.composing||e.$set(e.Htype,"name",t.target.value)}}})])])]),e._v(" "),a("div",{staticClass:"btn_wrapper"},[a("div",{staticClass:"submit_btn",on:{click:e.submitBasicInfo}},[e._v("确认")])]),e._v(" "),a("div",{directives:[{name:"show",rawName:"v-show",value:e.identityType,expression:"identityType"}],staticClass:"select_identity_box",on:{click:function(t){e.changeidentityType(0)}}},[a("div",{staticClass:"mask_wrapper"}),e._v(" "),a("mt-picker",{attrs:{itemHeight:80,"show-toolbar":!1,slots:e.identityList},on:{change:e.onValuesIdentityChange}})],1),e._v(" "),a("div",{directives:[{name:"show",rawName:"v-show",value:e.hospitalType,expression:"hospitalType"}],staticClass:"select_hospital_box",on:{click:function(t){e.changeHospitalType(0)}}},[a("div",{staticClass:"mask_wrapper"}),e._v(" "),a("mt-picker",{attrs:{itemHeight:80,"show-toolbar":!1,valueKey:"name",slots:e.hospitalList},on:{change:e.onValuesHospitalChange}})],1)])},staticRenderFns:[function(){var e=this.$createElement,t=this._self._c||e;return t("div",{staticClass:"head_wrapper common_wrapper"},[t("div",{staticClass:"logo"},[t("div",{staticClass:"upload_btn"})])])}]};var c=a("VU/8")(o,l,!1,function(e){a("fPME")},"data-v-be5a1d64",null);t.default=c.exports}});