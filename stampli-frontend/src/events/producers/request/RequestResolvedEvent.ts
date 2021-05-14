import { RequestResponse } from '../../../services/request/response/RequestResponse';

export class RequestResolvedEvent<T> {
  constructor(public requestResponse: RequestResponse<T>) {}
}
