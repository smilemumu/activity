import request from '../utils/request'

export function getDepartments() {
  return request.get('/dict/departments')
}

export function getProducts() {
  return request.get('/dict/products')
}

export function getOrganizations() {
  return request.get('/dict/organizations')
}

export function getIndustries() {
  return request.get('/dict/industries')
}
