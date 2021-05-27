package com.example.controller.stamps.challenge;

import com.example.service.stamps.challenge.service.ChallengeService;
import com.example.service.stamps.challenge.service.ChallengingTokenDTO;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/business/challenge/{businessId}")
public class ChallengeController {
    ChallengeService challengeService;

    public ChallengeController(ChallengeService challengeService) {
        this.challengeService = challengeService;
    }

    // TODO: Authentication should contain businesses that user can access
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AcquireTokenDto acquireToken(Authentication authentication, @PathVariable Integer businessId) {
        final var acquiredToken = challengeService.acquireToken((Integer) authentication.getPrincipal(), businessId);
        return new AcquireTokenDto(acquiredToken.getIssuerId(), acquiredToken.getBusinessId(), acquiredToken.getChallengeNonce());
    }

    @PostMapping(path = "/{issuerId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ClaimProofDto claimChallenge(
            Authentication authentication,
            @RequestBody ClaimChallengeDto body,
            @PathVariable Integer businessId,
            @PathVariable Integer issuerId
    ) {
        final var claimProof = challengeService.claimToken(new ChallengingTokenDTO(
                issuerId,
                businessId,
                (Integer) authentication.getPrincipal(),
                body.getNonce()
        ));

        return new ClaimProofDto(claimProof.getClaimerId(), claimProof.getIssuerId(), claimProof.getBusinessId());
    }
}
