import React, { useContext, useEffect, useState } from 'react';
import { InjectionContext } from '../../../stated/context/InjectionContext';
import { BusinessStampsComponent } from './BusinessStampsComponent';
import Logger from 'js-logger';

export const BusinessStampsStated = ({ businessId, userId }: { businessId: number, userId?: number }) => {
  const servicesBundle = useContext(InjectionContext);

  const stampService = servicesBundle.stampService;
  const businessInfoService = servicesBundle.businessInfoService;
  const tokenService = servicesBundle.tokenService;


  const [state, updateState] = useState<[boolean, number, string]>([false, 0, '']);

  useEffect(
    () => (
      Promise.all([stampService.getStamps(businessId, userId || tokenService.getUserId() || 0), businessInfoService.getLogoSrc(businessId)]).then(
        ([quantityOfStamps, logoSrc]) => {
          Logger.debug(`quantity: ${quantityOfStamps} and srd: ${logoSrc}`);
          updateState([true, quantityOfStamps, logoSrc]);
        }
      ),
        undefined
    ),
    []
  );

  const [loaded, quantityOfStamps, logoSrc] = state;

  return loaded ? <BusinessStampsComponent stampsQuantity={quantityOfStamps} logoSrc={logoSrc} /> : null;
};
