export interface RequestResponse<P> {
  status: number;
  headers: Record<string, string>;
  payload?: P;

  isSuccessful(): boolean;
}
