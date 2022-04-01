import React from 'react';
import styled from 'styled-components';

const Inner = styled.div`
  padding: .6rem 1.2rem;
  margin: 1rem;
  width: 20rem;
  background: #fa875a;
  border-radius: .6rem;
  cursor: pointer;
  display: flex;
  justify-content: center;
  text-transform: uppercase;
`

export default function Button (params: {text: string, link: string, bg: string, color: string}) {
    return (
        <Inner>
            <p>{params.text}</p>
        </Inner>
    )
}