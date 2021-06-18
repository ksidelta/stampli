import React, { useEffect } from 'react';
import styled from 'styled-components';
import { Stamp } from '../../../simple/stamp/Stamp';
import Logger from 'js-logger';
import { Delimeter } from '../../../simple/container/flex/Delimeter';
import { SimpleButton } from '../../../simple/buttons/SimpleButton';

const MAX_NUMBER_OF_STAMPS = 10;


const StyledBusinessStampsView = styled.div`
  width: 80%;
  margin: auto;
  flex-grow: 1;

  & .logo {
    width: 30%;
    margin: auto;

    & > img {
      width: 100%;
    }
  }

  & .stamps {
    width: 100%;
    display: flex;
    flex-direction: row;
    flex-wrap: wrap;
    justify-content: space-around;

    & > * {
      width: 15%;
      margin: 2% 7%;
    }
  }
`;


export const BusinessStampsComponent = ({
                                          logoSrc,
                                          stampsQuantity,
                                          onClick
                                        }: { logoSrc: string; stampsQuantity: number, onClick?: () => void }) => {
  useEffect(() => Logger.debug(`${logoSrc} and ${stampsQuantity}`), []);

  return (
    <StyledBusinessStampsView>
      <div className={'logo'}>
        <Stamp img={logoSrc} isOn={true} />
      </div>
      <Delimeter />
      <div className={'stamps'}>
        {[...Array(MAX_NUMBER_OF_STAMPS).keys()].map(key => (
          <Stamp img={logoSrc} isOn={key < stampsQuantity} key={key} />
        ))}
      </div>
      <Delimeter />

      <div>
        <SimpleButton text={'OK'} onClick={() => onClick && onClick()} />
      </div>
    </StyledBusinessStampsView>
  );
};
