import { ElementNode, element } from './element';
import { LayoutParamAttributes } from './layoutparams';
import { MainTypes } from "./main";
// types
/* generated @ 2018-11-13T11:41:10.284 */
export namespace WidgetMediaTypes {
  export interface MediaControlView2Attributes extends MainTypes.ViewGroupAttributes {
  }
  export interface VideoView2Attributes extends MainTypes.ViewGroupAttributes {
  }
}
// elements
export const VideoView2 = (
  attributes?: WidgetMediaTypes.VideoView2Attributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<WidgetMediaTypes.VideoView2Attributes, LayoutParamAttributes> => {
  return element('videoView2', attributes, children);
};
export const MediaControlView2 = (
  attributes?: WidgetMediaTypes.MediaControlView2Attributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<WidgetMediaTypes.MediaControlView2Attributes, LayoutParamAttributes> => {
  return element('mediaControlView2', attributes, children);
};