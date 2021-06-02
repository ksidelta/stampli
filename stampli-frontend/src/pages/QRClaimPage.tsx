import { MobilePage } from './base/MobilePage';
import { Header } from '../components/simple/Header';
import { faCircle } from '@fortawesome/free-solid-svg-icons/faCircle';
import { Content } from '../components/simple/container/layout/structure/Content';
import React, { useContext, useEffect } from 'react';
import { CenterMiddle } from '../components/simple/container/layout/CenterMiddle';
import { InjectionContext } from '../components/stated/context/InjectionContext';
import { useParams } from 'react-router-dom';
import { endpointMap } from '../services/request/constants/EndpointMap';

export const QRClaimPage = () => {
  const servicesBundle = useContext(InjectionContext);

  const { businessId, issuerId, nonce } = useParams<{ businessId: string; issuerId: string; nonce: string }>();

  useEffect(() => {
    servicesBundle.requestService
      .query(endpointMap.CLAIM_BUSINESS_QR_CHALLENGE(parseInt(businessId), parseInt(issuerId)), 'post', {}, { nonce })
      .then(x => console.log(x.payload));
  }, []);

  return (
    <MobilePage>
      <Header title={'Twoje pieczątki'} icon={faCircle} />
      <Content>
        <CenterMiddle>{`${businessId} ${issuerId} ${nonce}`}</CenterMiddle>
      </Content>
    </MobilePage>
  );
};
