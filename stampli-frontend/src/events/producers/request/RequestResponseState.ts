import { RequestResponse } from '../../../services/request/response/RequestResponse';
import { makeAutoObservable } from 'mobx';

export class RequestResponseState<T> {
  response: RequestResponse<T> | undefined;
  isRequestInProgress: boolean;

  protected constructor() {
    this.response = undefined;
    this.isRequestInProgress = true;
  }

  updateResponse(response: RequestResponse<T>) {
    this.response = response;
    this.isRequestInProgress = false;
  }

  public static create<T>(): RequestResponseState<T> {
    return makeAutoObservable(new RequestResponseState<T>());
  }
}
