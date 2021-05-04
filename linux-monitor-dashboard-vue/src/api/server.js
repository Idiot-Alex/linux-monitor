import axios from './config';

/**
 * get server list
 * @param {
 *  pageNo: pageNo
 *  pageSize: pageSize
 *  host: host
 * } params 
 * @returns 
 */
export function getServerList(params) {
  return axios({
    url: '/server/list',
    method: 'get',
    params
  })
}

/**
 * get server properties
 * @returns 
 */
export function getServerProperties() {
  return axios({
    url: '/server/properties',
    method: 'get'
  })
}

/**
 * edit server
 * @param {
 *  id: ID
 *  host: host
 *  user: user
 *  password: password
 *  port: port
 * } params
 * @returns 
 */
export function editServer(params) {
  return axios({
    url: '/server',
    method: 'post',
    data: params
  })
}