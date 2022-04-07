export interface StampService {
  getStamps(businessId: number, userId: number): Promise<number>;
}
