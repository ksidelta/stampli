import { RequestResponse } from './RequestResponse';

export class BasicRequestResponse<PAYLOAD> implements RequestResponse<PAYLOAD> {
  constructor(protected status: number, public headers: Record<string, string>, public payload?: PAYLOAD) {}

  isSuccessful(): boolean {
    return [200, 201].includes(this.status);
  }

  isNotFound(): boolean {
    return [400].includes(this.status);
  }
}
