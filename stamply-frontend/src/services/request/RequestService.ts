import { RequestResponse } from './response/RequestResponse';

export interface RequestService {
  query<PAYLOAD, RESPONSE>(
    path: string,
    method: 'get' | 'post' | 'put',
    headers?: Record<string, string>,
    payload?: PAYLOAD
  ): Promise<RequestResponse<RESPONSE>>;
}
