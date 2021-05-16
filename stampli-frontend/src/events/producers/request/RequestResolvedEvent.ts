import { RequestResponse } from '../../../services/request/response/RequestResponse';
import { InputEvent } from '../input/InputEvent';

export class RequestResolvedEvent<T> implements InputEvent<T> {
  constructor(public requestResponse: RequestResponse<T>) {}
}
