import { MobilePage } from './base/MobilePage';
import { Header } from '../components/simple/Header';
import { Content } from '../components/simple/container/layout/structure/Content';
import React, { useContext, useEffect, useState } from 'react';
import { CenterMiddle } from '../components/simple/container/layout/CenterMiddle';
import { InjectionContext } from '../components/stated/context/InjectionContext';
import { InputState } from '../state/form/input/InputState';
import { ChallengeQRCode } from '../components/simple/qr/ChallengeQRCode';
import { Routes } from '../router/routes/Routes';
import { action } from 'mobx';
import { map } from 'rxjs/operators';
import { faArrowLeft } from '@fortawesome/free-solid-svg-icons/faArrowLeft';
import { observer, Observer } from 'mobx-react';
import { ChallengeDTO } from '../services/business/admin/challenge/BusinessChallengeService';
import { BusinessStampsStated } from '../components/complex/business/stamps/BusinessStampsStated';

export const QRBusinessDisplayPage = observer(() => {
  const servicesBundle = useContext(InjectionContext);

  const [qrState] = useState(() => InputState.createStringInputState());
  const [currentChallenge, update] = useState<[false] | [true, StampClaimedSocketDTO]>([false]);

  const updateChallenge = () => {
    servicesBundle.businessChallengeService.getChallenge().then(x => {
      action(() => {
        qrState.valueState.value = Routes.challenge.claim(
          servicesBundle.config.baseUrl,
          x.businessId,
          x.issuerId,
          x.nonce
        );
      })();
    });
  };

  useEffect(() => {
    (async () => {
      const businessId = await servicesBundle.businessProfileService.getCurrentBusinessId();
      const issuerId = servicesBundle.tokenService.getUserId();

      servicesBundle.socket
        .watch(`/business/challenge/${businessId}/${issuerId}`)
        .pipe(
          map(content => content.body),
          map(body => JSON.parse(body))
        )
        .subscribe(x => {
          console.log(x);
          update([true, x]);
          updateChallenge();
          console.log('SUBARASHI');
        });

      updateChallenge();
    })();
  }, []);

  return (
    <MobilePage>
      <Header
        title={'Przekaż kod'}
        icon={faArrowLeft}
        onClick={() => servicesBundle.routingService.goToBusinessOptionsPage()}
      />
      <Content>
        <CenterMiddle>
          {currentChallenge[0] ? <BusinessStampsStated businessId={currentChallenge[1].businessId}
                                                       userId={currentChallenge[1].claimerId} /> :
            <Observer>{() => <ChallengeQRCode state={qrState} onClick={updateChallenge} />}</Observer>}
        </CenterMiddle>
      </Content>
    </MobilePage>
  );
});

type StampClaimedSocketDTO = {
  issuerId: number;
  businessId: number;
  claimerId: number;
  challengeNonce: number;
};
