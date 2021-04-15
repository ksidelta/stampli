export interface RegisterService {
  register(username: string, password: string): Promise<void>;
}
