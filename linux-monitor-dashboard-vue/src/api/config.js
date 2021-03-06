import axios from 'axios';
import Vue from 'vue';

const domain = document.getElementById('domainPath').value;

const request = axios.create({
  timeout: 10000,
  headers: {
    'Content-type': 'application/json;charset=UTF-8'
  },
  responseType: 'json'
})

// request interceptor
request.interceptors.request.use(config => {
  // if domain is not null
  if (domain) {
    // set config url
    config.url = (domain + config.url).replaceAll('//', '/');
  }
  return config;
}, error => {
  return Promise.reject(error);
});

// response interceptor
request.interceptors.response.use(response => {
  const res = response.data;
  // if request failed
  if (res.code !== 200) {
    Vue.prototype.$notify.error({
      title: 'Request Failed',
      message: res.message,
      offset: 100
    });
  }
  return res;
}, error => {
  const { status, statusText } = error;
  if (!status) {
    Vue.prototype.$notify.warning({
      title: 'Network Error',
      message: 'please check your network...',
      offset: 100
    });
  } else {
    Vue.prototype.$notify.error({
      title: status,
      message: statusText,
      offset: 100
    });
  }
  return Promise.reject(error);
});

export default request;