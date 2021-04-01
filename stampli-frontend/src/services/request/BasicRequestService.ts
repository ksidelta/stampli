import { Configuration } from '../config/Configuration';
import { RequestService } from './RequestService';
import { RequestResponse } from './response/RequestResponse';
import axios, { AxiosResponse } from 'axios';
import { BasicRequestResponse } from './response/BasicRequestResponse';

export class BasicRequestService implements RequestService {
  constructor(protected config: Configuration) {}

  query<PAYLOAD, RESPONSE>(
    path: string,
    method: 'get' | 'post',
    headers: Record<string, string> = {},
    payload?: PAYLOAD
  ): Promise<RequestResponse<RESPONSE>> {
    return axios[method](`${this.config.baseUrl}${path}`, payload, {
      headers: { ...this.defaultHeaders(), ...headers }
    })
      .catch(error => {
        if (error.response) {
          return this.toRequestResponse(error.response as AxiosResponse<RESPONSE>);
        }
        return Promise.reject();
      })
      .then(x => this.toRequestResponse(x as AxiosResponse<RESPONSE>));
  }

  private toRequestResponse<PAYLOAD>(response: AxiosResponse<PAYLOAD>): RequestResponse<PAYLOAD> {
    return new BasicRequestResponse(response.status, response.headers, response.data as PAYLOAD);
  }

  private defaultHeaders() {
    return {
      'content-type': 'application/json',
      accept: 'application/json'
    };
  }
}
