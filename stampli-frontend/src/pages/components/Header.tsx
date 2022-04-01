import React from 'react';
import styled from 'styled-components';

const Inner = styled.header`
   display: flex;
   flex-flow: row nowrap;
`

export default function Header(props: {title: string}) {
    return (
        <>
            <Inner>
                <img alt="left-arrow" src="/assets/arrow_back_ios_new_black_24dp.svg"/>
                <h1>{props.title}</h1>
            </Inner>
        </>
    )
}