export interface BusinessChallengeService {
  getChallenge(): Promise<ChallengeDTO>;
}

export class ChallengeDTO {
  constructor(public issuerId: number, public businessId: number, public nonce: number) {}
}
