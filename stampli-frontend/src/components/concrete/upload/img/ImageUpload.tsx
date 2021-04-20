import React from 'react';
import ReactImageUploading, { ImageListType } from 'react-images-uploading';
import styled from 'styled-components';
import Rectangle from 'react-rectangle';
import { BasicLabel } from '../../label/BasicLabel';
import { Center } from '../../../simple/container/layout/align/Center';
import { CenterMiddle } from '../../container/layout/CenterMiddle';

export const ImageUpload = () => {
  const [images, setImages] = React.useState<ImageListType>([]);

  return (
    <div>
      <BasicLabel>{'Ustaw obrazek dla pieczątek'}</BasicLabel>
      <Center>
        <ReactImageUploading value={images} onChange={setImages}>
          {({ onImageUpload }) => (
            <ImageHolder onClick={onImageUpload}>
              <Rectangle aspectRatio={[1, 1]}>
                <CenterMiddle>
                  {images[0]?.dataURL ? <img src={images[0].dataURL} alt={'nullo'} /> : 'KURWA'}
                </CenterMiddle>
              </Rectangle>
            </ImageHolder>
          )}
        </ReactImageUploading>
      </Center>
    </div>
  );
};

const ImageHolder = styled.div`
  width: 60%;
  border: 1px solid black;

  & img {
    max-width: 100%;
    max-height: 100%;
  }
`;
