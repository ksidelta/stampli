export interface RequestResponse<P> {
  headers: Record<string, string>;
  payload?: P;

  isSuccessful(): boolean;

  isNotFound(): boolean;

  isDuplicate(): boolean;

  asType<T>(ctor: { new (): T }): RequestResponse<T>;

  withConversion<T>(conversion: (payload: any) => T): RequestResponse<T>;
}
