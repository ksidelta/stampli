import React from 'react';
import styled from 'styled-components';
import Rectangle from 'react-rectangle';

export const Stamp = ({ img, isOn }: { img: string; isOn: boolean }) => (
  <StyledStamp>
    <Rectangle>{isOn && <img src={img} />}</Rectangle>
  </StyledStamp>
);

const StyledStamp = styled.div`
  border: 0.2rem solid black;
  border-radius: 50%;
  overflow: hidden;
  box-sizing: border-box;

  & img {
    width: 100%;
    height: 100%;
  }
`;
