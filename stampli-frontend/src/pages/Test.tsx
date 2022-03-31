import styled from 'styled-components';
import React from 'react';


export const Test = () =>
  <Main>
    <Flow>
      <Logo>
        <BoldTitle>
          <Title text="XD"/>
        </BoldTitle>
      </Logo>
      <Subtitle>
        {'W czym mogę Ci dzisiaj pomóc?'}
      </Subtitle>

    </Flow>
  </Main>;


;

const Title = ({text}: {text: string}) => <>{text}</>


const Flow = styled.div`
  display: flex;
  flex-direction: column;
`;

const Main = styled(Flow)`
  width: 100%;
  height: 100%;
  margin: 0;
  padding: 0;
`

const BoldTitle = styled.div`
  text-align: center;
  
  font-size: 4rem;
  padding: 2rem 0;
  font-weight: 900;
  font-family: Roboto;
`;

const SubTitle = styled.span`

`

const Logo = styled.div``;

const Subtitle = styled.div``;
