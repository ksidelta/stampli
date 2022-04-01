import React from 'react';
import styled from 'styled-components';
import "../../img/arrow_back_ios_new_black_24dp.svg";

const Inner = styled.header`
  display: flex;
  flex-flow: row nowrap;
  font-family: "Century Gothic";
  font-size: 1.25rem;
  margin: .8rem 1.2rem;
  > img {
    width: 3rem;
    margin-right: -3rem;
  }
  > h1 {
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