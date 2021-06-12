import React from 'react';
import styled from 'styled-components';

export const Stamp = ({ img, isOn }: { img: string; isOn: boolean }) => (
  <StyledStamp>{isOn && <img src={img} />}</StyledStamp>
);

const StyledStamp = styled.div`
  width: 10rem;
  height: 10rem;
  border: 1rem solid black;
  border-radius: 50%;
`;
