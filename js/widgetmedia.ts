/* generated @ 2018-11-09T15:28:49.672 */
import { element } from "./element";
import { ElementNode } from "./element.types";
import { LayoutParamAttributes } from "./index.types";
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