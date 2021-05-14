import { BasicRequestResponse } from './BasicRequestResponse';

export function create200BasicRequestResponse(payload: any) {
  return new BasicRequestResponse(200, {}, payload);
}
