import axios from 'axios';
axios.defaults.timeout = 5000; //超时时间是5秒
axios.defaults.withCredentials = true; //允许跨域
//Content-type 访问方式 响应头
// 设置post请求头
// axios.defaults.headers.post['Content-type'] = 'application/x-www-form-urlencoded;charset=UTF-8';
axios.defaults.headers.post['Content-type'] = 'multipart/form-data;charset=UTF-8';
//基础url
axios.defaults.baseURL = "http://localhost:8888";

//响应拦截器
axios.interceptors.response.use(
  (response) => {
    //如果response里面的status是200，说明访问到接口了，否则错误
    if (response.status == 200) {
      return Promise.resolve(response);
    } else {
      return Promise.reject(response);
    }
  },
  (error) => {
    if (error.response.status) {
      switch (error.response.status) {
        //未登录
        case 401:
          //让其转向登录页面
          router.replace({
            path: '/',
            query: {
              redirect: router.currentRoute.fullPath
            }
          });
          break;
          //没找到
        case 404:
          break;
      }
      return Promise.reject(error.response);
    }
  }
);

/* 
 * 封装get方法
 */
export function get(url, params = {}) {
  return new Promise((resolve, reject) => {
    axios.get(url, {
        params: params
      })
      .then(response => {
        resolve(response.data);
      })
      .catch(err => {
        reject(err);
      })
  });
}

/* 
 * 封装post方法
 */
export function post(url, data = {}) {
  return new Promise((resolve, reject) => {
    axios.post(url, data)
      .then(response => {
        resolve(response.data);
      })
      .catch(err => {
        reject(err);
      })
  });
}

/**
   * 封装delete请求
   *
   */

  export function deletes(url, data = {}) {
    return new Promise((resolve, reject) => {
      axios.delete(url, data)
        .then(response => {
          resolve(response.data);
        }, err => {
          reject(err)
        })
    })
  }
  
  /**
     * 封装put请求
     * 
     */
  
  export function put(url, data = {}) {
    return new Promise((resolve, reject) => {
      axios.put(url, data)
        .then(response => {
          resolve(response.data)
        }, err => {
          reject(err)
        })
    })
  }
