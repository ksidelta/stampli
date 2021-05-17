import { RequestService } from './RequestService';
import { RequestResponse } from './response/RequestResponse';
import axios, { AxiosResponse, ResponseType } from 'axios';
import { BasicRequestResponse } from './response/BasicRequestResponse';
import Logger from 'js-logger';
import { Configuration } from '../config/Configuration';

export class BasicRequestService implements RequestService {
  constructor(protected config: Pick<Configuration, 'baseUrl'>) {}

  query<PAYLOAD, RESPONSE>(
    path: string,
    method: 'get' | 'post' | 'put',
    headers: Record<string, string> = {},
    payload?: PAYLOAD
  ): Promise<RequestResponse<RESPONSE>> {
    return axios[method](`${this.config.baseUrl}${path}`, payload, {
      responseType: detectDataTypeByHeader(headers),
      headers: { ...this.defaultHeaders(), ...headers }
    })
      .catch(error => {
        if (error.response) {
          return this.toRequestResponse(error.response as AxiosResponse<RESPONSE>);
        }
        return Promise.reject();
      })
      .then(x => {
        Logger.debug(`query headers: ${JSON.stringify(x.headers, undefined, 2)}`);
        return this.toRequestResponse(x as AxiosResponse<RESPONSE>);
      });
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

function detectDataTypeByHeader(headers: Record<string, string>): ResponseType {
  if (/image\/.*/.test(headers['accept'])) {
    return 'blob';
  }

  return 'blob';
}
