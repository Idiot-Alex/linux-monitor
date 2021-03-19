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