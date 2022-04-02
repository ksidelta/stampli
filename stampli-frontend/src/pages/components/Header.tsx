import React from 'react';
import styled from 'styled-components';
import "../../img/arrow_back_ios_new_black_24dp.svg";

const Inner = styled.header`
  display: flex;
  flex-flow: row nowrap;
  font-family: "Century Gothic";
  align-items: center;
  margin: .8rem 1.2rem;
  > img {
    height: 1.5rem;
    margin-right: -2rem;
  }
  > h1 {
    font-size: 1.25rem;
    margin: 0 auto;
  }
`

export default function Header(props: {title: string}) {
    return (
        <Inner>
            <img alt="left-arrow" src="/assets/img/arrow_back_ios_new_black_24dp.svg"/>
            <h1>{props.title}</h1>
        </Inner>
    )
}