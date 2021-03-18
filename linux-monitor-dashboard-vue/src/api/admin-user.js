import axios from './config';

/**
 * admin user login
 * @param {
 *  userName: userName
 *  password: password
 * } params 
 * @returns 
 */
export function adminUserLogin(params) {
  return axios({
    url: '/admin-user/login',
    method: 'get',
    params
  })
}

/**
 * admin user list
 * @param {
 *  pageNo: pageNo
 *  pageSize: pageSize
 *  userName: userName
 * } params 
 * @returns 
 */
export function getAdminUserList(params) {
  return axios({
    url: '/admin-user/list',
    method: 'get',
    params
  })
}

/**
 * get admin user
 * @param {int} id 
 * @returns 
 */
export function getAdminUser(id) {
  return axios({
    url: `/admin-user/${id}`,
    method: 'get'
  })
}

/**
 * create admin user
 * @param {
 *  userName: userName
 *  password: password
 * } data
 * @returns 
 */
export function createAdminUser(data) {
  return axios({
    url: '/admin-user',
    method: 'post',
    data
  })
}

/**
 * update admin user
 * @param {
 *  id: id
 *  userName: userName
 *  password: password
 * } data 
 * @returns 
 */
export function updateAdminUser(data) {
  return axios({
    url: `/admin-user/${data.id}`,
    method: 'put',
    data
  })
}

/**
 * delete batch admin user
 * @param {Array} data 
 * @returns 
 */
export function deleteAdminUser(data) {
  return axios({
    url: '/admin-user/batch',
    method: 'delete',
    data
  })
}
