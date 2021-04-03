export interface RequestResponse<P> {
  headers: Record<string, string>;
  payload?: P;

  isSuccessful(): boolean;
  isNotFound(): boolean;
}
