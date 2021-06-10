export interface StampService {
  getStamps(businessId: number): Promise<number>;
}
