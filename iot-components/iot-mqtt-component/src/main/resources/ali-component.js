!function(n){"use strict";function d(n,t){var r=(65535&n)+(65535&t);return(n>>16)+(t>>16)+(r>>16)<<16|65535&r}function f(n,t,r,e,o,u){return d((u=d(d(t,n),d(e,u)))<<o|u>>>32-o,r)}function l(n,t,r,e,o,u,c){return f(t&r|~t&e,n,t,o,u,c)}function g(n,t,r,e,o,u,c){return f(t&e|r&~e,n,t,o,u,c)}function v(n,t,r,e,o,u,c){return f(t^r^e,n,t,o,u,c)}function m(n,t,r,e,o,u,c){return f(r^(t|~e),n,t,o,u,c)}function c(n,t){var r,e,o,u;n[t>>5]|=128<<t%32,n[14+(t+64>>>9<<4)]=t;for(var c=1732584193,f=-271733879,i=-1732584194,a=271733878,h=0;h<n.length;h+=16)c=l(r=c,e=f,o=i,u=a,n[h],7,-680876936),a=l(a,c,f,i,n[h+1],12,-389564586),i=l(i,a,c,f,n[h+2],17,606105819),f=l(f,i,a,c,n[h+3],22,-1044525330),c=l(c,f,i,a,n[h+4],7,-176418897),a=l(a,c,f,i,n[h+5],12,1200080426),i=l(i,a,c,f,n[h+6],17,-1473231341),f=l(f,i,a,c,n[h+7],22,-45705983),c=l(c,f,i,a,n[h+8],7,1770035416),a=l(a,c,f,i,n[h+9],12,-1958414417),i=l(i,a,c,f,n[h+10],17,-42063),f=l(f,i,a,c,n[h+11],22,-1990404162),c=l(c,f,i,a,n[h+12],7,1804603682),a=l(a,c,f,i,n[h+13],12,-40341101),i=l(i,a,c,f,n[h+14],17,-1502002290),c=g(c,f=l(f,i,a,c,n[h+15],22,1236535329),i,a,n[h+1],5,-165796510),a=g(a,c,f,i,n[h+6],9,-1069501632),i=g(i,a,c,f,n[h+11],14,643717713),f=g(f,i,a,c,n[h],20,-373897302),c=g(c,f,i,a,n[h+5],5,-701558691),a=g(a,c,f,i,n[h+10],9,38016083),i=g(i,a,c,f,n[h+15],14,-660478335),f=g(f,i,a,c,n[h+4],20,-405537848),c=g(c,f,i,a,n[h+9],5,568446438),a=g(a,c,f,i,n[h+14],9,-1019803690),i=g(i,a,c,f,n[h+3],14,-187363961),f=g(f,i,a,c,n[h+8],20,1163531501),c=g(c,f,i,a,n[h+13],5,-1444681467),a=g(a,c,f,i,n[h+2],9,-51403784),i=g(i,a,c,f,n[h+7],14,1735328473),c=v(c,f=g(f,i,a,c,n[h+12],20,-1926607734),i,a,n[h+5],4,-378558),a=v(a,c,f,i,n[h+8],11,-2022574463),i=v(i,a,c,f,n[h+11],16,1839030562),f=v(f,i,a,c,n[h+14],23,-35309556),c=v(c,f,i,a,n[h+1],4,-1530992060),a=v(a,c,f,i,n[h+4],11,1272893353),i=v(i,a,c,f,n[h+7],16,-155497632),f=v(f,i,a,c,n[h+10],23,-1094730640),c=v(c,f,i,a,n[h+13],4,681279174),a=v(a,c,f,i,n[h],11,-358537222),i=v(i,a,c,f,n[h+3],16,-722521979),f=v(f,i,a,c,n[h+6],23,76029189),c=v(c,f,i,a,n[h+9],4,-640364487),a=v(a,c,f,i,n[h+12],11,-421815835),i=v(i,a,c,f,n[h+15],16,530742520),c=m(c,f=v(f,i,a,c,n[h+2],23,-995338651),i,a,n[h],6,-198630844),a=m(a,c,f,i,n[h+7],10,1126891415),i=m(i,a,c,f,n[h+14],15,-1416354905),f=m(f,i,a,c,n[h+5],21,-57434055),c=m(c,f,i,a,n[h+12],6,1700485571),a=m(a,c,f,i,n[h+3],10,-1894986606),i=m(i,a,c,f,n[h+10],15,-1051523),f=m(f,i,a,c,n[h+1],21,-2054922799),c=m(c,f,i,a,n[h+8],6,1873313359),a=m(a,c,f,i,n[h+15],10,-30611744),i=m(i,a,c,f,n[h+6],15,-1560198380),f=m(f,i,a,c,n[h+13],21,1309151649),c=m(c,f,i,a,n[h+4],6,-145523070),a=m(a,c,f,i,n[h+11],10,-1120210379),i=m(i,a,c,f,n[h+2],15,718787259),f=m(f,i,a,c,n[h+9],21,-343485551),c=d(c,r),f=d(f,e),i=d(i,o),a=d(a,u);return[c,f,i,a]}function i(n){for(var t="",r=32*n.length,e=0;e<r;e+=8)t+=String.fromCharCode(n[e>>5]>>>e%32&255);return t}function a(n){var t=[];for(t[(n.length>>2)-1]=void 0,e=0;e<t.length;e+=1)t[e]=0;for(var r=8*n.length,e=0;e<r;e+=8)t[e>>5]|=(255&n.charCodeAt(e/8))<<e%32;return t}function e(n){for(var t,r="0123456789abcdef",e="",o=0;o<n.length;o+=1)t=n.charCodeAt(o),e+=r.charAt(t>>>4&15)+r.charAt(15&t);return e}function r(n){return unescape(encodeURIComponent(n))}function o(n){return i(c(a(n=r(n)),8*n.length))}function u(n,t){return function(n,t){var r,e=a(n),o=[],u=[];for(o[15]=u[15]=void 0,16<e.length&&(e=c(e,8*n.length)),r=0;r<16;r+=1)o[r]=909522486^e[r],u[r]=1549556828^e[r];return t=c(o.concat(a(t)),512+8*t.length),i(c(u.concat(t),640))}(r(n),r(t))}function t(n,t,r){return t?r?u(t,n):e(u(t,n)):r?o(n):e(o(n))}"function"==typeof define&&define.amd?define(function(){return t}):"object"==typeof module&&module.exports?module.exports=t:n.md5=t}(this);
var md5=this.md5;
/**
    网关上线
    订阅：/sys/hbtgIA0SuVw9lxjB/AA:BB:CC:DD:10/c/#

    子设备注册：
    /sys/hbtgIA0SuVw9lxjB/AA:BB:CC:DD:10/s/register
    {
        "id": "6",
        "params":{
            "productKey":"Rf4QSjbm65X45753",
            "deviceName":"ABC12400001",
            "model":"S1"
        }
    }
    子设备上线
    订阅：/sys/Rf4QSjbm65X45753/ABC12400001/c/#

    数据上报：
    /sys/Rf4QSjbm65X45753/ABC12400001/s/event/property/post
    {
        "id": "6",
        "params":{
            "powerstate": 1
        }
    }
     */

var registered={};

function getPkDn(clientId){
  var arr=clientId.split("|")[0].split("&");

    if(arr.length<2){
        throw new Error("incorrect clientid");
    }
    var model="";
    if(arr.length3=2){
        model=arr[2];
    }
  return {
	pk:arr[0],
	dn:arr[1],
      model: model
  };
}

function register(payload){
  var auth= JSON.parse(payload);
  // 兼容ali
  var device= getPkDn(auth.clientid);

  var pwd=md5("xdkKUymrEGSCYWswqCvSPyRSFvH5j7CU"+auth.clientid);
  // if(pwd.toLocaleLowerCase()!=auth.password.toLocaleLowerCase()){
	// throw new Error("incorrect password");
  // }
  return {
	type:"register",
	data:{
	  productKey:device.pk,
	  deviceName:device.dn,
	  model:device.model
	}
  };
}

function subRegister(topic,parent,payload){
  var params= payload.params;
  var reply=
	  {
		productKey:parent.productKey,
		deviceName:parent.deviceName,
		mid:"0",
		content:{
		  topic:topic.replace("/s/","/c/")+"_reply",
		  payload:JSON.stringify({
			id:"0",
			code:0,
			data:{
			  "productKey":params.productKey,
			  "deviceName":params.deviceName
			}
		  })
		}
	  };

  return {
	type:"register",
	data:{
	  productKey:parent.productKey,
	  deviceName:parent.deviceName,
	  subDevices:[{
		productKey:params.productKey,
		deviceName:params.deviceName,
		model:params.model
	  }]
	},
	action:{
	  type:"ack",
	  content:JSON.stringify(reply)
	}
  };
}

function deviceStateChange(head,clientId,state){
  var topic=head.topic;
  var device=getPkDn(clientId);

  var arr= topic.split('/');
  if(arr.length<6){
	throw new Error("incorrect topic")
  }

  var pk=arr[2];
  var dn=arr[3];
  return {
	type:"state",
	data:{
	  productKey:pk,
	  deviceName:dn,
	  state:state,
	  parent:{
		productKey:device.pk,
		deviceName:device.dn,
	  }
	}
  }
}

function disconnect(clientId){
  var device=getPkDn(clientId);
  return {
	type:"state",
	data:{
	  productKey:device.pk,
	  deviceName:device.dn,
	  state:"offline"
	}
  }
}

function ignore(){
    return {
    }
}

function aliDeviceInfoUpdate(pk, dn, payload){
    return ignore();
    // return {
    //     type:"report",
    //     data:{
    //         productKey:pk,
    //         deviceName:dn,
    //         mid:payload.id,
    //         content:{
    //             topic:topic,
    //             payload:payload.params
    //         }
    //     },
    //     action:{}
    // };
}

//必须提供onReceive方法
// todo: if else改成map
this.onReceive=function(head,type,payload){
  if(type=='auth'){
	return register(payload);
  }

  if(type=='subscribe'){
	return deviceStateChange(head,payload,'online');
  }

  if(type=='unsubscribe'){
	return deviceStateChange(head,payload,'offline');
  }

  if(type=='disconnect'){
	return disconnect(payload);
  }

  var topic=head['topic'];
  if(!topic){
	throw new Error("topic is blank")
  }

  var arr= topic.split('/');
  if(arr.length<6){
	throw new Error("incorrect topic")
  }
  var pk=arr[2];
  var dn=arr[3];
  payload=JSON.parse(payload);

  //子设备注册
  if(topic.endsWith('/register')){
	return subRegister(topic,{productKey:pk,deviceName:dn},payload);
  }

  // 阿里设备信息上传
    // Received message:{"id": "1", "version": "1.0", "params": [{"domain": "SYSTEM", "attrKey": "SYS_SDK_LANGUAGE", "attrValue": "Python"}, {"domain": "SYSTEM", "attrKey": "SYS_LP_SDK_VERSION", "attrValue": "1.0.0"}, {"domain": "SYSTEM", "attrKey": "SYS_SDK_IF_INFO", "attrValue": "Eth|03ACDEFF0032|Eth|03ACDEFF0031"}], "method": "thing.deviceinfo.update"}, with QoS AT_MOST_ONCE
    // topic":"/sys/JfGrfkfPXpBCEb6k/test_sc_001/thing/deviceinfo/update
  if(topic.endsWith("/thing/deviceinfo/update")){
      return aliDeviceInfoUpdate(pk, dn, payload);
  }

  //数据上报
  var reply=
      {
        productKey:pk,
        deviceName:dn,
        mid:payload.id,
        content:{
          topic:topic.replace("/s/","/c/")+"_reply",
          payload:JSON.stringify({
            id:payload.id,
            method: payload.method+"_reply",
            code:0,
          })
        }
      };

    var action={};
    if(!topic.endsWith("_reply")){
        //需要回复的消息
        action={
          type:"ack",
          content:JSON.stringify(reply)
        }
    }

  return {
	type:"report",
	data:{
	  productKey:pk,
	  deviceName:dn,
	  mid:payload.id,
	  content:{
		topic:topic,
		payload:payload.params
	  }
	},
	action:action
  }
}

this.onRegistered=function(regInfo,result){
}