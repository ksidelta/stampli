import { RequestResponse } from './RequestResponse';
import { plainToClass } from 'class-transformer';

export class BasicRequestResponse<PAYLOAD> implements RequestResponse<PAYLOAD> {
  constructor(protected status: number, public headers: Record<string, string>, public payload?: PAYLOAD) {}

  isSuccessful(): boolean {
    return [200, 201].includes(this.status);
  }

  isNotFound(): boolean {
    return [400].includes(this.status);
  }

  isDuplicate(): boolean {
    return [400].includes(this.status);
  }

  asType<T>(ctor: { new (): T }): RequestResponse<T> {
    const toDecorate: RequestResponse<T> = Object.create(this);

    toDecorate.payload = plainToClass(ctor, this.payload);

    return toDecorate;
  }
}
