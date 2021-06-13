import React, { useEffect } from 'react';
import styled from 'styled-components';
import { Stamp } from '../../../simple/stamp/Stamp';
import Logger from 'js-logger';

const MAX_NUMBER_OF_STAMPS = 10;

export const BusinessStampsComponent = ({ logoSrc, stampsQuantity }: { logoSrc: string; stampsQuantity: number }) => {
  useEffect(() => Logger.debug(`${logoSrc} and ${stampsQuantity}`), []);

  return (
    <StyledBusinessStampsView>
      <div>
        <img src={logoSrc} />
      </div>
      // image
      <div>
        {[...Array(MAX_NUMBER_OF_STAMPS).keys()].map(key => (
          <Stamp img={logoSrc} isOn={key < stampsQuantity} />
        ))}
      </div>
      // matrix of stamps
      <div></div> // button
    </StyledBusinessStampsView>
  );
};

const StyledBusinessStampsView = styled.div``;
