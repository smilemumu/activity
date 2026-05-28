import request from '../utils/request'

export function getPvUv(activityId) {
  return request.get('/report/pv-uv', { params: { activityId } })
}

export function getRewardStock(activityId) {
  return request.get('/report/reward-stock', { params: { activityId } })
}

export function getRewardClaimCount(activityId) {
  return request.get('/report/reward-claim-count', { params: { activityId } })
}

export function getClaimRecords(params) {
  return request.get('/report/claim-records', { params })
}
