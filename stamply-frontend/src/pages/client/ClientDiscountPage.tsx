import React from 'react';
import styled from "styled-components";
import '../../img/menu_placeholder.jpg';
import BottomBar from "../../components/BottomBar";
import {MobilePage} from "../base/MobilePage";

const Inner = styled.div`
    min-height: 100vh;
    display: grid;
    grid-template-rows: 1fr auto;
    > section {
      display: flex;
      flex-flow: column nowrap;
      justify-content: center;
      align-items: center;
      overflow: hidden;
    }
`

export default function DiscountPage() {
    return (
        <MobilePage>
            <Inner>
                <section>
                    <img src={'/assets/img/menu_placeholder.jpg'} alt="Restaurant's menu card" height="300"/>
                    <img src={'/assets/img/menu_placeholder.jpg'} alt="Restaurant's menu card" height="300"/>
                    <img src={'/assets/img/menu_placeholder.jpg'} alt="Restaurant's menu card" height="300"/>
                    <img src={'/assets/img/menu_placeholder.jpg'} alt="Restaurant's menu card" height="300"/>
                    <img src={'/assets/img/menu_placeholder.jpg'} alt="Restaurant's menu card" height="300"/>
                </section>
                <BottomBar/>
            </Inner>
        </MobilePage>
    )
}