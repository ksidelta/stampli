export interface LoadingState {
  isLoading(): boolean;

  load(): void;

  finish(): void;

  fail(): void;
}
