import React from 'react';
import styled from 'styled-components';
import Box from '@mui/material/Box';
import { Typography } from '@mui/material';
import Stamp from './Stamp';

const Inner = styled(Box)(({ theme }) => ({
  width: '405px',
  height: '644px',
  background: theme.palette.primary.main,
  borderRadius: '2rem', // TODO Add box shadow using mui
  display: 'flex',
  flexFlow: 'column nowrap'
}));

const StampsContainer = styled.div`
  display: flex;
  flex-flow: row wrap;
  justify-content: center;
  height: fit-content;
  margin: auto;
`;

const StyledTypography = styled(Typography)(({ theme }) => ({
  color: 'white',
  margin: '2rem auto'
}));

const DominoBrick = styled.div`
  display: flex;
  flex-direction: column;
`

const TitleNumber = styled.span`
  display: block;
  text-align: center;
`

const NumberedStamp = function (num: number) {
  return (
    <DominoBrick>
      <TitleNumber key={num}>{num}.</TitleNumber>
      <Stamp key={num} collected={num < 4} />
    </DominoBrick>
  );
};
// const stamps = Array.from(Array(10).keys()).map(NumberedStamp);
const stamps: JSX.Element[] = [];
for (let i = 1; i <= 10; i++) {
  stamps.push(NumberedStamp(i));
}

export default function StampCard() {

  return (
    <Inner>
      <StyledTypography variant="h4">Darmowa Kawa</StyledTypography>
      <StampsContainer>{stamps}</StampsContainer>
    </Inner>
  );
}
