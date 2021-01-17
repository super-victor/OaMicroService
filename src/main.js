import Vue from 'vue'
import App from './App'

import {myRequest} from './utils/api'

import {myRequest2} from './utils/api2'

Vue.config.productionTip = false

Vue.prototype.$request = myRequest;
Vue.prototype.$request2 = myRequest2;

App.mpType = 'app'

const app = new Vue({
  ...App
})
app.$mount()
