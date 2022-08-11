import React from 'react';
import styled from 'styled-components';
import Box from '@mui/material/Box';
import { Typography } from '@mui/material';
import dummyStampleImage from '../img/approval_black_24dp.svg';

interface Props {
  collected?: boolean;
}

const Inner = styled(Box)(({ theme }) => ({
  width: '55px',
  height: '55px',
  margin: '.8rem 2rem',
  borderRadius: '50%',
  background: theme.palette.primary.light
}));

const BgImg = styled.img<Props>`
  width: 100%;
  height: 100%;
  opacity: ${props => (props.collected ? 1 : 0.5)};
`;

export default function Stamp(props: Props) {
  return (
    <Inner>
      <BgImg src={dummyStampleImage} alt="Pieczątka" collected={props.collected} />
    </Inner>
  );
}
