import React from 'react';
import { Header } from '../components/concrete/Header';
import { MobilePage } from './base/MobilePage';
import { IconButton } from '../components/concrete/buttons/IconButton';
import { faArrowRight } from '@fortawesome/free-solid-svg-icons/faArrowRight';
import { faCircle } from '@fortawesome/free-solid-svg-icons/faCircle';
import { VerticalFlex } from '../components/concrete/container/flex/VerticalFlex';

export const EntryPage = () => (
  <MobilePage>
    <Header title={'W czym mogę Ci dzisiaj pomóc?'} icon={faCircle} />
    <VerticalFlex>
      <IconButton text={'Chcę zacząć zbierać pieczątki!'} icon={faArrowRight} />
      <IconButton
        text={'Chcę rozdawać pieczątki!'}
        icon={faArrowRight}
        onClick={history => history.goToBusinessOptionsPage()}
      />
      <IconButton text={'Chcę dowiedzieć się więcej!'} icon={faArrowRight} />
    </VerticalFlex>
  </MobilePage>
);
