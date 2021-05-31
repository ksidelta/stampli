export const endpointMap = {
  LOGIN_BASIC: '/api/authentication/login/basic',
  REGISTER_BASIC: '/api/authentication/register/basic',
  BUSINESS: '/api/business/',
  BUSINESS_NAME: (id: number) => `/api/business/${id}/name`,
  BUSINESS_LOGO: (id: number) => `/api/business/${id}/logo`,
  BUSINESS_QR_CHALLENGE: (businessId: number) => `/api/business/challenge/${businessId}`
};
