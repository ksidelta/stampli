export interface ExternalTokenService {
  regenerateToken(token: string): Promise<void>;
}