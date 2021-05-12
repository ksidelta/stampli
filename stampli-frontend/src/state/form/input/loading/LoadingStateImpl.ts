import { LoadingState } from './LoadingState';

export class LoadingStateImpl implements LoadingState {
  constructor(protected loading: boolean = false) {}

  finish(): void {
    this.loading = false;
  }

  isLoading(): boolean {
    return this.loading;
  }

  load(): void {
    this.loading = true;
  }

  fail(): void {
    this.loading = false;
  }
}
