import { RequestResponse } from './RequestResponse';

export class BasicRequestResponse<PAYLOAD> implements RequestResponse<PAYLOAD> {
  constructor(public status: number, public headers: Record<string, string>, public payload?: PAYLOAD) {}

  isSuccessful(): boolean {
    return [200, 201].includes(this.status);
  }
}
