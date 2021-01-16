import Vue from 'vue'
import App from './App'

import {myRequest} from './utils/api'

Vue.config.productionTip = false

Vue.prototype.$request = myRequest;

App.mpType = 'app'

const app = new Vue({
  ...App
})
app.$mount()
