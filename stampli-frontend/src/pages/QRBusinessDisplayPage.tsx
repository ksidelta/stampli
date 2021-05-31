import { MobilePage } from './base/MobilePage';
import { Header } from '../components/simple/Header';
import { faCircle } from '@fortawesome/free-solid-svg-icons/faCircle';
import { Content } from '../components/simple/container/layout/structure/Content';
import React, { useContext, useEffect, useState } from 'react';
import { CenterMiddle } from '../components/simple/container/layout/CenterMiddle';
import { InjectionContext } from '../components/stated/context/InjectionContext';
import { InputState } from '../state/form/input/InputState';
import { ChallengeQRCode } from '../components/simple/qr/ChallengeQRCode';

export const QRBusinessDisplayPage = () => {
  const servicesBundle = useContext(InjectionContext);

  const [qrState] = useState(() => InputState.createStringInputState());

  const businessName = useEffect(
    () => (
      servicesBundle.businessChallengeService.getChallenge().then(x => {
        qrState.valueState.value = `TWOJ STARY: ${x.businessId} ${x.issuerId} ${x.nonce}`;
      }),
      undefined
    ),
    []
  );

  return (
    <MobilePage>
      <Header title={'Przekaż kod'} icon={faCircle} />
      <Content>
        <CenterMiddle>
          <ChallengeQRCode state={qrState} />
        </CenterMiddle>
      </Content>
    </MobilePage>
  );
};
