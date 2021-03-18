import axios from './config';

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
